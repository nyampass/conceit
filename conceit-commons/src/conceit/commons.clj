(ns conceit.commons
  (require conceit.commons.ns))

(def ^{:private true} sub-namespaces '#{type assert class fn map number string char byte coll date flow http meta multimethod regex ns})

(doseq [sub-ns sub-namespaces]
  (let [sub-ns-full (symbol (str (name (ns-name *ns*)) "." (name sub-ns)))]
    (require sub-ns-full)
    (conceit.commons.ns/intern-publics *ns* sub-ns-full)))
