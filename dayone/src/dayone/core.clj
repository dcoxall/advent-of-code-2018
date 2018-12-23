(ns dayone.core
  (:gen-class))

(use '[clojure.string :only (trim)])

(defn read-input [] (line-seq (java.io.BufferedReader. *in*)))
(defn parse-int [line] (Integer/parseInt (trim line) 10))
(defn part-one [] (reduce (fn [acc line] (+ acc (parse-int line))) 0 (read-input)))

(defn reached-twice
  ([s] (reached-twice #{0} 0 s))
  ([visited acc s]
  (let [value (parse-int (first s)) total (+ value acc)]
    (if (visited total) total (recur (conj visited total) total (rest s))))))

(defn part-two [] (reached-twice (cycle (read-input))))

(defn -main
  [& args]
  (if (= "partone" (first args))
    (println (part-one))
    (println (part-two))))
