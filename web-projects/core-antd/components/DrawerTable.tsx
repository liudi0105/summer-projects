import { ReactNode } from "@liudi0105/core-react";
import { Drawer, DrawerProps, TableProps } from "antd";
import { useState } from "react";

export type DrawerTableProps = {
  trigger: ReactNode;
  drawerTitle: ReactNode;
  drawerWidth?: string | number;
} & DrawerProps &
  TableProps;

export const DrawerTable = (props: DrawerTableProps) => {
  const { drawerWidth, trigger, drawerTitle } = props;
  const [open, setOpen] = useState(false);

  return (
    <>
      <div onClick={() => setOpen(true)}>{trigger}</div>
      <Drawer
        title={drawerTitle}
        destroyOnClose
        width={drawerWidth}
        open={open}
        onClose={() => setOpen(false)}
      ></Drawer>
    </>
  );
};
