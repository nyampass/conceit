(ns conceit.commons.file)

(defn recursive-children
  ([dir directory-filter]
     (let [children (.listFiles dir)]
       (if (empty? children)
         nil
         (mapcat (fn [child]
                   (cond
                    (or (= (.getName child) ".") (= (.getName child) "..")) []
                    (and (.isDirectory child) (directory-filter child)) (cons child (recursive-children child directory-filter))
                    :else [child]))
                 (sort children)))))
  ([dir]
     (recursive-children dir identity)))
