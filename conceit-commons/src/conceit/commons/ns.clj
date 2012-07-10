(ns conceit.commons.ns)

(defn intern-with-var [ns sym var]
  (let [new-var (intern ns (with-meta sym (meta var)) (var-get var))]
    (when (.isDynamic var) (.setDynamic new-var))
    new-var))

(defn intern-publics [dest-ns src-ns]
  (doseq [[sym var] (ns-publics src-ns)]
    (intern-with-var dest-ns sym var)))
