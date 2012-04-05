(ns conceit.commons.class-loader
  (import [java.io File]))

(def ^{:private true} object #())

(defn classloader-resource-path [path]
  (.. (class object) getClassLoader (getResource path) getPath))

(defn resource-path [path]
  (if (.exsits (File. path))
    path
    (classloader-resource-path path)))
