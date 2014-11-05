(ns ttfe.board)

(defn -find-empty-tiles [board]
  (reduce (fn [acc row-n]
            (let [row (nth board row-n)]
              (concat acc (reduce (fn [col-acc col-n]
                                    (let [tile (nth row col-n)]
                                      (if (nil? tile)
                                        (conj col-acc [row-n col-n])
                                        col-acc)))
                                  '()
                                  (range (count row))))))
          '()
          (range (count board))))

(defn add-tile [board]
  (let [[rand-row rand-col] (rand-nth (-find-empty-tiles board))
        new-tile-value (rand-nth '(2 2 2 2 2 2 2 2 2 4))]
    (assoc-in board [rand-row rand-col] new-tile-value)))

(defn new-board []
  (add-tile [[nil nil nil nil]
             [nil nil nil nil]
             [nil nil nil nil]
             [nil nil nil nil]]))

(defn move-right [board]
  (mapv (fn [line]
          (let [numbers (filter #(not= % nil) line)
                rnumbers (reverse numbers)
                crunched-numbers (loop [acc [] i 0]
                                   (if (>= i (count numbers))
                                     acc
                                     (if (= (nth rnumbers i) (nth rnumbers (inc i) nil))
                                       (recur (conj acc (* 2 (nth rnumbers i)))
                                              (+ i 2))
                                       (recur (conj acc (nth rnumbers i))
                                              (inc i)))))]
            (vec (concat (repeat (-  (count line) (count crunched-numbers))
                                 nil)
                         (reverse crunched-numbers)))))
        board))

(defn move-left [board]
  (mapv (fn [line]
          (let [numbers (filter #(not= % nil) line)
                crunched-numbers (loop [acc [], i 0]
                                   (if (>= i (count numbers))
                                     acc
                                     (if (= (nth numbers i) (nth numbers (inc i) nil))
                                       (recur (conj acc (* 2 (nth numbers i)))
                                              (+ i 2))
                                       (recur (conj acc (nth numbers i))
                                              (inc i)))))]
            (vec (concat crunched-numbers
                         (repeat (-  (count line) (count crunched-numbers))
                                 nil)))))
        board))

(defn -rotate-cw [board]
  (apply mapv (fn [a b c d] [a b c d]) (vec (reverse board))))

(defn -rotate-ccw [board]
  (vec (reverse (apply mapv (fn [a b c d] [a b c d]) board))))

(defn move-up [board]
  (-rotate-ccw (move-right (-rotate-cw board))))

(defn move-down [board]
  (-rotate-ccw (move-left (-rotate-cw board))))

(defn movements-left? [board]
  (let [board-moved-left (move-left board)
        board-moved-right (move-right board)
        board-moved-up (move-up board)
        board-moved-down (move-down board)]
    (not= board-moved-left board-moved-right board-moved-up board-moved-down)))
