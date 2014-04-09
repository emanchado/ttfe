(ns ttfe.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [ttfe.board :refer [move-left move-right move-up move-down
                                new-board add-tile movements-left?]]))

(enable-console-print!)

(defn new-game []
  {:board (new-board)
   :already-won false
   :paused false})

(def app-state
  (atom
   (new-game)))

(def move-fns [move-up move-right move-down move-left])

(defn divs-for-board [board]
  (reduce (fn [row-acc row-n]
            (let [row (nth board row-n)]
              (concat row-acc
                    (reduce (fn [acc col-n]
                              (let [cell (nth row col-n)]
                                (if (nil? cell)
                                  acc
                                  (conj acc
                                        (dom/div #js {:className (str "tile tile-" cell " tile-position-" (inc col-n) "-" (inc row-n))}
                                                 (dom/div #js {:className "tile-inner"} cell))))))
                            []
                            (range (count row))))))
          '()
          (range (count board))))

(defn tiles-view [app owner]
  (reify
    om/IRender
    (render [_]
      (let [cell-divs (divs-for-board (:board app))]
        (apply dom/div
               #js {:id "tiles" :className "tile-container"}
               cell-divs)))))

(defn end-game [class message]
  (let [msg-cont (. js/document (querySelector ".game-message"))]
    ; The game could have been won and continued, so let's make sure
    ; the "game won" mark is not there when the game is lost after
    ; winning
    (.remove (.-classList msg-cont) "game-won")
    (.add (.-classList msg-cont) class)
    (-> msg-cont
        (.getElementsByTagName "p")
        (.item 0)
        .-textContent
        (set! message))))

(defn init []
  (let [input-manager (js/KeyboardInputManager.)]
    (.on input-manager
         "move"
         (fn [direction]
           (if-not (:paused @app-state)
             (let [move-fn (nth move-fns direction)]
               (swap! app-state
                      (fn [state]
                        (let [board (:board state)
                              moved-board (move-fn board)]
                          (if (not= board moved-board)
                            (update-in state [:board]
                                       (fn [b]
                                         (add-tile moved-board)))
                            state))))
               (when (and (contains? (set (flatten (:board @app-state))) 2048)
                          (not (:already-won @app-state)))
                 (swap! app-state (fn [state] (merge state {:already-won true
                                                           :paused true})))
                 (end-game "game-won" "You win!"))
               (when-not (movements-left? (:board @app-state))
                 (end-game "game-over" "Game over!"))))))
    (.on input-manager
         "restart"
         (fn []
           (let [msg-cont (. js/document (querySelector ".game-message"))]
             (.remove (.-classList msg-cont) "game-won")
             (.remove (.-classList msg-cont) "game-over")
             (swap! app-state
                    (fn [state]
                      (new-game))))))
    (.on input-manager
         "keepPlaying"
         (fn []
           (swap! app-state (fn [state]
                              (update-in state [:paused] (fn [_] false))))
           (let [msg-cont (. js/document (querySelector ".game-message"))]
             (.remove (.-classList msg-cont) "game-won")
             (.remove (.-classList msg-cont) "game-over"))))
    (om/root tiles-view
             app-state
             {:target (. js/document (getElementById "tiles"))})))
