(ns conceit.commons.http
  (require [conceit.commons
            [map :as map]
            [string :as string]
            [number :as number]]
           [clojure
            [string :as clj.string]])
  (import [java.net URLDecoder URLEncoder]))

(defn parse-query-string
  ([s text-encoding]
     (if (empty? s)
       {}
       (map/map-to-map (fn [pair]
                         (map #(URLDecoder/decode % text-encoding)
                              (clj.string/split pair #"=" 2)))
                       (clj.string/split s #"&"))))
  ([s]
     (parse-query-string s "UTF-8")))

(defmulti url-encode type)

(defmethod url-encode String [s]
  (URLEncoder/encode s "UTF-8"))

(defmethod url-encode clojure.lang.Named [value]
  (url-encode (name value)))

(defmethod url-encode :default [value]
  (url-encode (str value)))

(defmethod url-encode clojure.lang.IPersistentMap [m]
  (clj.string/join "&" (map (fn [[key value]] (str (url-encode key) "=" (url-encode value))) m)))

(defn parse-http-language-tags [tags-string]
  (if (string/non-empty-string? tags-string)
    (map/filter-map pos?
                    (map/map-to-map (fn [[language & options]]
                                      [(.toLowerCase language) (or (when-let [q-value (second (first (filter (fn [[name alue]] (= name "q"))
                                                                                                             (map #(clj.string/split % #"\s*=\s*") options))))]
                                                                     (number/double-from q-value))
                                                                   1)])
                                    (map #(clj.string/split % #"\s*;\s*") (clj.string/split tags-string #"\s*,\s*"))))
    {}))
