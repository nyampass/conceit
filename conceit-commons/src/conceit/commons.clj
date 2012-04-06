(ns conceit.commons)

(def ^{:private true} sub-namespaces '#{assert class fn map number string type char coll date flow http meta multimethod regex})

(doseq [sub-ns sub-namespaces]
  (load (str "commons/" (name sub-ns))))
