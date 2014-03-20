(ns clj2048.core-test
  (:require [clj2048.core :refer :all]
            [clojure.test :refer :all]))

(deftest test-move-right
  (testing "Can move a single number"
    (is (= (move-right [[nil nil  4  nil]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]])))

  (testing "Can move two numbers in different rows"
    (is (= (move-right [[nil nil  4  nil]
                        [nil nil nil nil]
                        [nil  2  nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil  2 ]
            [nil nil nil nil]])))

  (testing "Can combine simple numbers"
    (is (= (move-right [[nil nil  2   2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]])))

  (testing "Can combine chains of numbers"
    (is (= (move-right [[ 4   4   2   2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil  8   4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]])))

  (testing "Can combine numbers that aren't adjacent"
    (is (= (move-right [[nil  2  nil  2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]])))

  (testing "Number combination starts the right way"
    (is (= (move-right [[ 2   2  nil  2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil  2   4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]))))


(deftest test-move-left
  (testing "Can move two numbers in different rows"
    (is (= (move-left [[nil nil  4  nil]
                       [nil nil nil nil]
                       [nil  2  nil nil]
                       [nil nil nil nil]])
           [[ 4  nil nil nil]
            [nil nil nil nil]
            [ 2  nil nil nil]
            [nil nil nil nil]]))))


(deftest test-move-up
  (testing "Can move two numbers in different rows"
    (is (= (move-up [[nil nil nil nil]
                     [nil nil  4  nil]
                     [nil  2  nil nil]
                     [nil nil nil nil]])
           [[nil  2   4  nil]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]))))


(deftest test-move-down
  (testing "Can move two numbers in different rows"
    (is (= (move-down [[nil nil nil nil]
                       [nil nil  4  nil]
                       [nil  2  nil nil]
                       [nil nil nil nil]])
           [[nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil  2   4  nil]]))))
