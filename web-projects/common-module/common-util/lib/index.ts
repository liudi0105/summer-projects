export function joinPath(...path: string[]) {
  return path
    .filter((v) => v != undefined)
    .map((v) => v.replace(/^\/+$/g, ""))
    .join("/");
}

export function deepCopy<T>(obj: T): T {
  return JSON.parse(JSON.stringify(obj));
}

export * from "./HttpClient";
