(ns conceit.commons)

(def ^{:private true} sub-namespaces '#{type assert class fn map number string char byte coll date flow http meta multimethod regex})

(doseq [sub-ns sub-namespaces]
  (load (str "commons/" (name sub-ns))))
