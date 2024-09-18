import { deepCopy, joinPath } from "@liudi0105/core-util";
import { ReactNode } from "@liudi0105/core-react";

export type RouterMenuItem = {
  path?: string;
  name: string;
  element?: ReactNode;
  children?: RouterMenuItem[];
  routes?: RouterMenuItem[];
};

export function routerMenu(routerMenuItems: RouterMenuItem[]) {
  const RouterM = deepCopy(routerMenuItems);
  RouterM.forEach((a) => {
    a.routes = a.children;
    a.element = undefined;
    a.children?.forEach((b) => {
      b.element = undefined;
      b.routes = b.children;
    });
  });

  const R: RouterMenuItem[] = [];
  routerMenuItems.forEach((a) => {
    a.children?.forEach((b) => {
      R.push({
        path: joinPath([a.path, b.path]),
        name: b.name,
        element: b.element,
      });
    });
  });

  return {
    menus: RouterM,
    routes: R,
  };
}
