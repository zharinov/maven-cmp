### Usage

```
java -jar maven-cmp.jar "1.2.4" "1.2.3" "1.2.3-RC1"
```

(should output `1.2.3-RC1 < 1.2.3 < 1.2.4`)

### Build

```
clj -T:build uber
```
