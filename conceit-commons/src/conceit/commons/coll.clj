(ns conceit.commons.coll)

(defn flatten-1 [coll]
  (apply concat coll))

(defn get-some [coll & keys]
  "Returns a value mapped some keys, or nil if any keys are not present."
  (some #(get coll %) keys))

(defn unique
  ([comp coll]
     ((fn next [contained coll]
        (if (empty? coll)
          nil
          (let [[first & rest] coll]
            (if (some #(comp first %) contained)
              (next contained rest)
              (lazy-seq (cons first (next (conj contained first) rest)))))))
      [] coll))
  ([coll]
     (unique = coll)))

(defn unique-by [f coll]
  ((fn next [contained coll]
     (if (empty? coll)
       nil
       (let [[first & rest] coll
             val (f first)]
         (if (contained val)
           (next contained rest)
           (lazy-seq (cons first (next (conj contained val) rest)))))))
   #{} coll))
