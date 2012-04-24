(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(def ^{:private true} primitive-array-types (into {} (map (fn [[name f]] [name (type (f 0))])
                                                          {:boolean boolean-array
                                                           :byte byte-array
                                                           :char char-array
                                                           :double double-array
                                                           :float float-array
                                                           :int int-array
                                                           :long long-array
                                                           :short short-array})))

(defn array-type
  "Returns a class of an array of instances of the specified type. Returns a class of a primitive array if :boolean, :byte, :char, :double, :float, :int, :long or :short are specified as a type."
  [type]
  (or (primitive-array-types type)
      (clojure.core/type (make-array type 0))))

(defn derive* [hierarchy & child-parent-pairs]
  (reduce (fn [hierarchy [child parent]]
            (derive hierarchy child parent))
          hierarchy
          (partition 2 child-parent-pairs)))

(defn make-hierarchy* [& child-parent-pairs]
  (apply derive* (make-hierarchy) child-parent-pairs))
