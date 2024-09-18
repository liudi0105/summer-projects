export function joinPath(path: Array<string | undefined>) {
  return path
    .filter((v) => v != undefined)
    .map((v) => v.replace(/^\/+$/g, ""))
    .join("/");
}

export function deepCopy<T>(obj: T): T {
  return JSON.parse(JSON.stringify(obj));
}
