(ns conceit.commons.coll)

(defmacro ^{:private true} defnthfn [name n]
  (let [sym (gensym)]
    `(do (defn ~name [~sym]
           (first ~(nth (iterate #(list `next %) sym) (dec n))))
         (alter-meta! #'~name assoc :arglists '([~'x])))))

(defnthfn third 3)
(defnthfn fourth 4)
(defnthfn fifth 5)
(defnthfn sixth 6)
(defnthfn seventh 7)
(defnthfn eighth 8)
(defnthfn ninth 9)
(defnthfn tenth 10)
(defnthfn forty-second 42)

(defn append [coll & vals]
  (concat coll vals))

(defn prepend [coll & vals]
  (concat vals coll))

(defn flatten-1 [coll]
  (apply concat coll))

(defn contains-in? [coll keys]
  (loop [coll coll keys keys]
    (cond (empty? keys) true
          (and (coll? coll) (contains? coll (first keys))) (recur (get coll (first keys)) (rest keys))
          :else false)))

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
