(ns ttfe.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [ttfe.board :refer [move-left move-right move-up move-down
                                add-tile]]))

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
                                        (dom/div #js {:className (str "tile title-" (inc row-n) " tile-position-" (inc col-n) "-" (inc row-n))}
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

(defn init []
  (let [input-manager (js/KeyboardInputManager.)]
    (.on input-manager
         "move"
         (fn [direction]
           (let [move-fn (nth move-fns direction)]
             (swap! app-state
                    (fn [state]
                      (let [board (:board state)]
                        (update-in state [:board] (fn [b]
                                                    (add-tile (move-fn b))))))))))
    (om/root tiles-view
             app-state
             {:target (. js/document (getElementById "tiles"))})))
