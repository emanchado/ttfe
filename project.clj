(defproject ttfe "0.1.0-SNAPSHOT"
  :description "Two Thousand Forty Eight - a simple 2048 clone in Clojure"
  :license {:name "BSD"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2156"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.5.0"]
                 [im.chit/purnam "0.4.3"]]

  :plugins [[lein-cljsbuild "1.0.2"]]

  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id "ttfe"
                        :source-paths ["src"]
                        :compiler {
                                   :output-to "ttfe.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "karma-test"
                        :source-paths ["src" "test"]
                        :compiler {:pretty-print true
                                   :externs ["lib/react-0.9.0.min.js"]
                                   :output-to "karma-test.js"
                                   :optimizations :whitespace}}]})
