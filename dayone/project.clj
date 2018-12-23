(defproject dayone "0.1.0-SNAPSHOT"
  :description "Advent of Code 2018 - Day One"
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot dayone.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
