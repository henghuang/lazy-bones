(ns lazy-bone.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))


(defn -main
  "oh. I'm tooo0 lazy"
  [& args]
  (let [root (io/file "./")
        save-to "./"
        filenames (->> (vec (.listFiles root))
                       (filter #(.isFile %))
                       (map #(.getName %))
                       (filter #(not (str/starts-with? % ".")))
                       (map #(str/split % #"\.")))
        types (->> (map last filenames)
                   (distinct))]
    (doseq [filename filenames]
      (let [in (io/file root (str/join "." filename))
            out (io/file save-to (last filename) (str/join "." filename))]
        (if (not (nil? (second filename)))
          (do  (io/make-parents out)
               (.renameTo in out)))
        ))))