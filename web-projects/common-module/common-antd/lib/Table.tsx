import {
  ParamsType,
  ProForm,
  ProFormInstance,
  ProFormText,
  ProTable,
  ProTableProps,
} from "@ant-design/pro-components";
import { Drawer, DrawerProps, message } from "antd";
import { ReactNode, useState } from "react";
import { ButtonModalFrom } from "./Form";
import { TriggerModal, TriggerModalProps } from "./Modal";
import { BaseEntity, BaseService } from "@common-module/common-api";

export type TableProps<
  DataType,
  ParamType extends ParamsType
> = {} & ProTableProps<DataType, ParamType>;

export const Table = <
  DataType extends BaseEntity,
  ParamType extends ParamsType
>(
  props: TableProps<DataType, ParamType>
) => {
  const { rowKey = "id" } = props;
  return <ProTable rowKey={rowKey} {...props}></ProTable>;
};

export type CrudTableProps<
  DataType extends BaseEntity,
  ParamType extends ParamsType
> = {
  crud?: boolean;
  createForm?: ProFormInstance;
  updateForm?: ProFormInstance;
  service: BaseService<DataType>;
} & TableProps<DataType, ParamType>;

export const CrudTable = <
  DataType extends BaseEntity,
  Params extends ParamsType
>(
  props: CrudTableProps<DataType, Params>
) => {
  const { toolbar = {}, crud = true, columns = [], service } = props;
  const { actions = [] } = toolbar;

  const [createForm] = ProForm.useForm(props.createForm);
  const [updateForm] = ProForm.useForm(props.updateForm);

  return (
    <Table
      {...props}
      toolbar={{
        ...toolbar,
        actions: [
          crud && (
            <ButtonModalFrom<DataType>
              title="添加"
              form={createForm}
              onFinish={async (values) => {
                await service.createOrUpdate(values);
                message.success("添加成功");
              }}
            >
              {columns
                .filter((v) => !!v.title)
                .map((v) => (
                  <ProFormText
                    key={v.dataIndex?.toString()}
                    name={v.dataIndex}
                    label={v.title?.toString() ?? "Unknown"}
                  />
                ))}
            </ButtonModalFrom>
          ),
          crud && (
            <ButtonModalFrom<DataType> title="更新" form={updateForm}>
              {columns
                .filter((v) => !!v.title)
                .map((v) => (
                  <ProFormText
                    key={v.dataIndex?.toString()}
                    name={v.dataIndex}
                    label={v.title?.toString() ?? "Unknown"}
                  />
                ))}
            </ButtonModalFrom>
          ),
          ...actions,
        ],
      }}
    />
  );
};

export type DrawerTableProps<
  DataSource extends BaseEntity,
  ParamType extends ParamsType
> = {
  trigger: ReactNode;
  drawerTitle: ReactNode;
  drawerWidth?: string | number;
} & DrawerProps &
  CrudTableProps<DataSource, ParamType>;

export const DrawerTable = <
  DataSource extends BaseEntity,
  Params extends ParamsType
>(
  props: DrawerTableProps<DataSource, Params>
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

export type ModalTableProps<
  DataSource extends BaseEntity,
  ParamType extends ParamsType
> = {
  trigger: ReactNode;
  drawerTitle: ReactNode;
  drawerWidth?: string | number;
} & TriggerModalProps &
  CrudTableProps<DataSource, ParamType>;

export const ModalTable = <
  DataSource extends BaseEntity,
  Params extends ParamsType
>(
  props: ModalTableProps<DataSource, Params>
) => {
  const { drawerWidth, drawerTitle, ...tableProps } = props;
  const [open, setOpen] = useState(false);

  return (
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
  );
};
