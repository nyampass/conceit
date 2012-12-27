(defproject conceit "1.0.0"
  :description "A web application framework in Clojure."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [conceit/conceit-commons "1.0.0"]]
  :sub ["conceit-commons" "conceit-validation"]
  :aot [conceit.core])
