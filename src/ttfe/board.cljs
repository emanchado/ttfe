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
