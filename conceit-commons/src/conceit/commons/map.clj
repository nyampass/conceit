(ns conceit.commons.map
  (use conceit.commons.meta))

(defn apply-with-map [f & args]
  (apply f (concat (butlast args)
                   (apply concat (last args)))))

(defn map-from-pairs [pairs]
  (into {} (map (fn [[k v]] [k v]) pairs)))

(defn filter-map [f map]
  (reduce (fn [result [key val]]
            (if (f val) result (dissoc result key)))
          map
          map))

(defn filter-map-by-key [f map]
  (reduce (fn [result [key val]]
            (if (f key) result (dissoc result key)))
          map
          map))

(defn remove-map [f map]
  (filter-map (complement f) map))

(defn remove-map-by-key [f map]
  (filter-map-by-key (complement f) map))

(defn map-to-map [f seq]
  (map-from-pairs (map f seq)))

(defn make-map-with-keys-by [f seq]
  (map-to-map #(vector (f %) %) seq))

(defn map-map [f map]
  (map-to-map (fn [[key val]] [key (f val)]) map))

(defn map-map-keys [f map]
  (map-to-map (fn [[key val]] [(f key) val]) map))

(defn deep-merge [& maps]
  (apply merge-with (fn [val1 val2]
                      (if (and (map? val1) (map? val2))
                        (deep-merge val1 val2)
                        val2))
         maps))

(defn keep-keys [map keys]
  (keep-meta map (reduce (fn [result key] (if (contains? map key)
                                            (assoc result key (get map key))
                                            result))
                         {}
                         keys)))
