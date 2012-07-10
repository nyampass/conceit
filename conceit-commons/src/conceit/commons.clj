(ns conceit.commons)

(def ^{:private true} sub-namespaces '#{type assert class fn map number string char byte coll date flow http meta multimethod regex})

(doseq [sub-ns sub-namespaces]
  (let [sub-ns-name (symbol (str (name (ns-name *ns*)) "." (name sub-ns)))]
    (require sub-ns-name)
    (doseq [[sym sub-var] (ns-publics (find-ns sub-ns-name))]
      (let [current-var (intern *ns* (with-meta sym (meta sub-var)) (var-get sub-var))]
        (when (.isDynamic sub-var) (.setDynamic current-var))))))
