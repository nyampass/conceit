(defproject conceit/conceit-commons "1.0.41"
  :description "Common libraries for Clojure."
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :dev-dependencies [[lein-ring "0.4.6"]
                     [swank-clojure "1.4.0"]]
  :java-source-paths ["src/java"]
  :aot [conceit.commons.java.Date
        conceit.commons
        conceit.commons.class-loader
        conceit.commons.csv
        conceit.commons.digest
        conceit.commons.file
        conceit.commons.http
        conceit.commons.image])
