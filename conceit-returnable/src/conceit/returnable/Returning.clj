(ns conceit.returnable.Returning
  (:gen-class :extends Throwable
              :init init
              :state attributes
              :constructors {[Object Object] [String]}))

(defn -init [returning-point value]
  [[(str "Return with " value)] {:returning-point returning-point :value value}])
