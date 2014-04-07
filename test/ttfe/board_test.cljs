(ns ttfe.board-test
  (:require [ttfe.board :refer [move-right move-left move-up move-down
                                movements-left?]]
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

(describe test-movements-left?
          (it "Movements left in trivial example"
              (is (movements-left? [[nil nil nil nil]
                                    [nil nil  4  nil]
                                    [nil  2  nil nil]
                                    [nil nil nil nil]])
                  true))

          (it "Movements left when only right/down is possible"
              (is (movements-left? [[ 2   4   8  128]
                                    [ 2   8   4   8 ]
                                    [ 4   2   32  4 ]
                                    [256  16  8  nil]])
                  true))

          (it "Movements left when only right/up is possible"
              (is (movements-left? [[ 2   4   8  nil]
                                    [ 2   8   4   8 ]
                                    [ 4   2   32  4 ]
                                    [256  16  8  128]])
                  true))

          (it "Movements left when only left/up is possible"
              (is (movements-left? [[nil  4   8   2 ]
                                    [ 2   8   4   8 ]
                                    [ 4   2   32  4 ]
                                    [256  16  8  128]])
                  true))

          (it "Movements left when only left/down is possible"
              (is (movements-left? [[256  4   8   2 ]
                                    [ 2   8   4   8 ]
                                    [ 4   2   32  4 ]
                                    [nil  16  8  128]])
                  true))

          (it "Movements left when only horizontal is possible"
              (is (movements-left? [[ 4   4   8   2 ]
                                    [ 2   8   4   8 ]
                                    [ 4   2   32  4 ]
                                    [256  16  8  128]])
                  true))

          (it "No movements left"
              (is (movements-left? [[ 4   2   8   2 ]
                                    [ 2   8   4   8 ]
                                    [ 4   2   32  4 ]
                                    [256  16  8  128]])
                  false)))
