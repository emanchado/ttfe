(ns clj2048.core
  (:require [lanterna.screen :as s]))

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
            (concat (repeat (-  (count line) (count crunched-numbers))
                                              nil)
                                      (reverse crunched-numbers))))
        board))

(defn main [screen-type]
  (let [screen (s/get-screen screen-type)]
    (s/in-screen screen
                 (s/put-string screen 0 0 "Clojure 2048!")
                 (s/put-string screen 0 1 "Press any key to exit...")
                 (s/redraw screen)
                 (s/get-key-blocking screen))))

(defn -main [& args]
  (let [args (set args)
        screen-type (cond
                      (args ":swing") :swing
                      (args ":text")  :text
                      :else           :auto)]
    (main screen-type)))
