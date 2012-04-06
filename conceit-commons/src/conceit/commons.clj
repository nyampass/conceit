(ns conceit.commons)

(def ^{:private true} required-sub-namespaces '#{assert class fn map number string type char coll date flow http meta multimethod regex test})

(doseq [sub-ns required-sub-namespaces]
  (let [sub-ns-name (symbol (str (ns-name *ns*) \. (name sub-ns)))]
    (require sub-ns-name)
    (when-let [sub-ns (find-ns sub-ns-name)]
      (doseq [[sym var] (ns-publics sub-ns)]
        (intern *ns* sym @var)))))
