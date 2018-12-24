(ns daytwo.core
  (:gen-class))

(use '[clojure.string :only (trim)])

(defn read-input [] (line-seq (java.io.BufferedReader. *in*)))

(defn increments
  [word]
  (let [freqs (vals (frequencies (trim word)))]
    (let [
      twos (if (some #(= 2 %) freqs) 1 0)
      threes (if (some #(= 3 %) freqs) 1 0)]
      [twos, threes])))


(defn part-one
  []
  (apply *
    (reduce
      (fn [[x y] word] (let [[a b] (increments word)] [(+ x a) (+ y b)]))
      [0 0]
      (read-input))))

(defn diff [a b] (reduce #(if (not (apply = %2)) (inc %1) %1) 0 (map vector a b)))

(defn locate-closest
  ([word words] (locate-closest word nil (count word) words))
  ([word closest score words]
    (if
      (not (empty? words))
      (let [w (first words) s (diff word w)]
        (if (and (not (= word w)) (< s score))
          (recur word w s (rest words))
          (recur word closest score (rest words))))
      {:word word :match closest :score score})))

(defn most-similar
  [words]
  (reduce
    #(let [similar (locate-closest %2 (rest words))] (if (< (:score similar) (:score %1)) similar %1))
    (locate-closest (first words) (rest words))
    words))

(defn identical-parts [a b] (reduce #(if (apply = %2) (str %1 (get %2 0)) %1) "" (map vector a b)))

(defn part-two
  []
  (let [similar (most-similar (read-input))]
    (identical-parts (:word similar) (:match similar))))

(defn -main
  [& args]
  (if (= "partone" (first args))
    (println (part-one))
    (println (part-two))))
