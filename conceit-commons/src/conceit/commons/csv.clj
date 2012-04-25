(ns conceit.commons.csv
  (require [clojure.string :as string]))

(defn csv-value [value]
  (str \" (string/replace (if (or (keyword? value) (symbol? value)) (name value) (str value)) "\"" "\"\"") \"))

(defn csv-row [values]
  (apply str (interpose \, (map csv-value values))))

(defn csv-rows [values-seq]
  (apply str (concat (map #(str (csv-row %) "\r\n") values-seq))))

(defn csv-row-by-map [map keys]
  (csv-row (reduce (fn [values key] (conj values (get map key))) [] keys)))

(defn csv-rows-by-maps [maps keys]
  (csv-rows (map #(reduce (fn [values key] (conj values (get % key))) [] keys) maps)))
