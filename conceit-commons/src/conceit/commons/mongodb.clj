(ns conceit.commons.mongodb
  (require [mongoika :as mongo]))

(defn restrict-by-id [id coll]
  (mongo/restrict :_id (mongo/object-id<- id) coll))

(defn restrict-by-ids [ids coll]
  (mongo/restrict :_id {:$in (set (map mongo/object-id<- ids))} coll))

(defn fetch-one-by-id [id coll]
  (mongo/fetch-one (restrict-by-id id coll)))

(defn update-by-id! [& update-operations-and-id-and-coll]
  (let [[id coll] (take-last 2 update-operations-and-id-and-coll)]
    (apply mongo/update! (concat (drop-last 2 update-operations-and-id-and-coll)
                                 [(restrict-by-id id coll)]))))

(defn grid-fs-data-by-id [id fs]
  (:data (fetch-one-by-id id (mongo/grid-fs fs))))

(defn exist? [query]
  (boolean (mongo/fetch-one (mongo/project :_id query))))
