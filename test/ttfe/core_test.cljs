(ns ttfe.core-test
  (:require [ttfe.board :refer [move-right move-left move-up move-down]]
            [purnam.test :refer [toSatisfy]])
  (:use-macros [purnam.test :only [describe it is is-not]]))

(describe test-move-right
  (it "Can move a single number"
    (is (move-right [[nil nil  4  nil]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]))

  (it "Can move two numbers in different rows"
    (is (move-right [[nil nil  4  nil]
                        [nil nil nil nil]
                        [nil  2  nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil  2 ]
            [nil nil nil nil]]))

  (it "Can combine simple numbers"
    (is (move-right [[nil nil  2   2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]))

  (it "Can combine chains of numbers"
    (is (move-right [[ 4   4   2   2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil  8   4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]))

  (it "Can combine numbers that aren't adjacent"
    (is (move-right [[nil  2  nil  2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil nil  4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]))

  (it "Number combination starts the right way"
    (is (move-right [[ 2   2  nil  2 ]
                        [nil nil nil nil]
                        [nil nil nil nil]
                        [nil nil nil nil]])
           [[nil nil  2   4 ]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]])))


(describe test-move-left
  (it "Can move two numbers in different rows"
    (is (move-left [[nil nil  4  nil]
                       [nil nil nil nil]
                       [nil  2  nil nil]
                       [nil nil nil nil]])
           [[ 4  nil nil nil]
            [nil nil nil nil]
            [ 2  nil nil nil]
            [nil nil nil nil]])))


(describe test-move-up
  (it "Can move two numbers in different rows"
    (is (move-up [[nil nil nil nil]
                     [nil nil  4  nil]
                     [nil  2  nil nil]
                     [nil nil nil nil]])
           [[nil  2   4  nil]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]])))


(describe test-move-down
  (it "Can move two numbers in different rows"
    (is (move-down [[nil nil nil nil]
                       [nil nil  4  nil]
                       [nil  2  nil nil]
                       [nil nil nil nil]])
           [[nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil  2   4  nil]])))
