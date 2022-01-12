(ns maven-cmp.core
  (:gen-class)
  (:require
   [clojure.string :as str])
  (:import
   [org.apache.maven.artifact.versioning ComparableVersion]))

(defn maven-cmp
  [^ComparableVersion x ^ComparableVersion y]
  (.compareTo x y))

(defn -main [& args]
  (let [versions (map #(ComparableVersion. %) args)
        sorted-versions (sort maven-cmp versions)
        version-pairs (partition 2 1 sorted-versions)
        relations (mapv #(let [[x y]   %
                               cmp-res (maven-cmp x y)]
                           (cond
                             (> cmp-res 0) " > "
                             (< cmp-res 0) " < "
                             :else " = "))
                        version-pairs)
        strings (interleave
                 (map #(.toString %) sorted-versions)
                 (conj relations ""))]
    (println
     (str/join "" strings))))
