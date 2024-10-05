import {
  ParamsType,
  ProTable,
  ProTableProps,
} from "@ant-design/pro-components";
import { Drawer, DrawerProps } from "antd";
import { ReactNode, useState } from "react";
import { Button } from "./Button";
import { TriggerModal, TriggerModalProps } from "./Modal";

export type TableProps<DataSource, ParamsType, ValueType> = {} & ProTableProps<
  DataSource,
  ParamsType,
  ValueType
>;

export const Table = <
  DataSource extends Record<string, unknown>,
  Params extends ParamsType,
  ValueType = "text"
>(
  props: TableProps<DataSource, Params, ValueType>
) => {
  const { rowKey = "id" } = props;
  return <ProTable rowKey={rowKey} {...props}></ProTable>;
};

export type CrudTableProps<DataSource, ParamsType, ValueType> = {
  crud?: boolean;
} & TableProps<DataSource, ParamsType, ValueType>;

export const CrudTable = <
  DataSource extends Record<string, unknown>,
  Params extends ParamsType,
  ValueType = "text"
>(
  props: CrudTableProps<DataSource, Params, ValueType>
) => {
  const { toolbar = {}, crud = true } = props;
  const { actions = [] } = toolbar;
  return (
    <Table
      {...props}
      toolbar={{
        ...toolbar,
        actions: [
          ...(crud
            ? [
                <ModalTable
                  drawerTitle="添加"
                  trigger={<Button>添加</Button>}
                />,
                <ModalTable
                  drawerTitle="更新"
                  trigger={<Button>更新</Button>}
                />,
              ]
            : []),
          ...actions,
        ],
      }}
    />
  );
};

export type DrawerTableProps<DataSource, ParamsType, ValueType> = {
  trigger: ReactNode;
  drawerTitle: ReactNode;
  drawerWidth?: string | number;
} & DrawerProps &
  CrudTableProps<DataSource, ParamsType, ValueType>;

export const DrawerTable = <
  DataSource extends Record<string, unknown>,
  Params extends ParamsType,
  ValueType = "text"
>(
  props: DrawerTableProps<DataSource, Params, ValueType>
) => {
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
      >
        <CrudTable {...props} />
      </Drawer>
    </>
  );
};

export type ModalTableProps<DataSource, ParamsType, ValueType> = {
  trigger: ReactNode;
  drawerTitle: ReactNode;
  drawerWidth?: string | number;
} & TriggerModalProps &
  CrudTableProps<DataSource, ParamsType, ValueType>;

export const ModalTable = <
  DataSource extends Record<string, unknown>,
  Params extends ParamsType,
  ValueType = "text"
>(
  props: ModalTableProps<DataSource, Params, ValueType>
) => {
  const { drawerWidth, trigger, drawerTitle, toolbar = {}, ...tableProps } = props;
  const [open, setOpen] = useState(false);

  return (
    <>
      <div onClick={() => setOpen(true)}>{trigger}</div>
      <TriggerModal
        title={drawerTitle}
        destroyOnClose
        width={drawerWidth}
        open={open}
        onClose={() => setOpen(false)}
        {...props}
      >
        <CrudTable {...tableProps} />
      </TriggerModal>
    </>
  );
};
