(ns ttfe.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [ttfe.board :refer [move-left move-right move-up move-down
                                add-tile movements-left?]]))

(enable-console-print!)

(def app-state
  (atom
   {:board [[ 4   4   2   2 ]
            [nil nil nil nil]
            [nil  8  nil nil]
            [nil nil nil nil]]}))

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
           (let [move-fn (nth move-fns direction)
                 new-state (swap! app-state
                                  (fn [state]
                                    (let [board (:board state)
                                          moved-board (move-fn board)]
                                      (if (not= board moved-board)
                                        (update-in state [:board]
                                                   (fn [b]
                                                     (add-tile moved-board)))
                                        state))))]
             (when (contains? (set (flatten (:board new-state))) 2048)
               (end-game "game-won" "You win!"))
             (when-not (movements-left? (:board new-state))
               (end-game "game-over" "Game over!")))))
    (.on input-manager
         "restart"
         (fn []
           (let [msg-cont (. js/document (querySelector ".game-message"))]
             (.remove (.-classList msg-cont) "game-won")
             (.remove (.-classList msg-cont) "game-over")
             (swap! app-state
                    (fn [state]
                      (update-in state [:board]
                                 (fn [b] (add-tile [[nil nil nil nil]
                                                   [nil nil nil nil]
                                                   [nil nil nil nil]
                                                   [nil nil nil nil]]))))))))
    (om/root tiles-view
             app-state
             {:target (. js/document (getElementById "tiles"))})))
