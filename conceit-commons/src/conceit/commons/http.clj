(ns conceit.commons.http
  (use [conceit.commons map string number])
  (require [clojure
            [string :as clj.string]])
  (import [java.net URLDecoder URLEncoder]))

(defn parse-query-string
  ([s text-encoding]
     (if (empty? s)
       {}
       (map-to-map (fn [pair]
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
  (if (non-empty-string? tags-string)
    (filter-map pos?
                (map-to-map (fn [[language & options]]
                              [(.toLowerCase language) (or (when-let [q-value (second (first (filter (fn [[name alue]] (= name "q"))
                                                                                                     (map #(clj.string/split % #"\s*=\s*") options))))]
                                                             (double-from q-value))
                                                           1)])
                            (map #(clj.string/split % #"\s*;\s*") (clj.string/split tags-string #"\s*,\s*"))))
    {}))
