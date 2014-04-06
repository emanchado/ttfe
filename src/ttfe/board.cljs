(ns ttfe.board)

(defn move-right [board]
  (mapv (fn [line]
          (let [numbers (filter #(not= % nil) line)
                crunched-numbers (loop [acc [] line (reverse numbers) i 0]
                                   (if (>= i (count numbers))
                                     acc
                                     (if (= (nth line i) (nth line (inc i) nil))
                                       (recur (conj acc (* 2 (nth line i)))
                                              line
                                              (+ i 2))
                                       (recur (conj acc (nth line i))
                                              line
                                              (inc i)))))]
            (vec (concat (repeat (-  (count line) (count crunched-numbers))
                                 nil)
                         (reverse crunched-numbers)))))
        board))

(defn move-left [board]
  (mapv (fn [line]
          (let [numbers (filter #(not= % nil) line)
                crunched-numbers (loop [acc [] line numbers i 0]
                                   (if (>= i (count numbers))
                                     acc
                                     (if (= (nth line i) (nth line (inc i) nil))
                                       (recur (conj acc (* 2 (nth line i)))
                                              line
                                              (+ i 2))
                                       (recur (conj acc (nth line i))
                                              line
                                              (inc i)))))]
            (vec (concat crunched-numbers
                         (repeat (-  (count line) (count crunched-numbers))
                                 nil)))))
        board))

(defn rotate-cw [board]
  (apply mapv (fn [a b c d] [a b c d]) (vec (reverse board))))

(defn rotate-ccw [board]
  (vec (reverse (apply mapv (fn [a b c d] [a b c d]) board))))

(defn move-up [board]
  (rotate-ccw (move-right (rotate-cw board))))

(defn move-down [board]
  (rotate-ccw (move-left (rotate-cw board))))

(defn find-empty-tiles [board]
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
  (let [[rand-row rand-col] (rand-nth (find-empty-tiles board))]
    (update-in board [rand-row rand-col] (fn [_] 2))))
