(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defn derive* [hierarchy & child-parent-pairs]
  (reduce (fn [hierarchy [child parent]]
            (derive hierarchy child parent))
          hierarchy
          (partition 2 child-parent-pairs)))

(defn make-hierarchy* [& child-parent-pairs]
  (apply derive* (make-hierarchy) child-parent-pairs))
