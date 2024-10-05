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

export type CrudTableProps<
  DataSource,
  ParamsType,
  ValueType extends BaseEntity
> = {
  crud?: boolean;
  createForm?: ProFormInstance;
  updateForm?: ProFormInstance;
  service: BaseService<ValueType>;
} & TableProps<DataSource, ParamsType, ValueType>;

export const CrudTable = <
  DataSource extends Record<string, unknown>,
  Params extends ParamsType,
  ValueType extends BaseEntity
>(
  props: CrudTableProps<DataSource, Params, ValueType>
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
            <ButtonModalFrom
              title="添加"
              form={createForm}
              onOk={async () => {
                message.success("添加成功");
                await service.createOrUpdate(await createForm.validateFields());
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
            <ButtonModalFrom title="更新" form={updateForm}>
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
  DataSource,
  ParamsType,
  ValueType extends BaseEntity
> = {
  trigger: ReactNode;
  drawerTitle: ReactNode;
  drawerWidth?: string | number;
} & DrawerProps &
  CrudTableProps<DataSource, ParamsType, ValueType>;

export const DrawerTable = <
  DataSource extends Record<string, unknown>,
  Params extends ParamsType,
  ValueType extends BaseEntity
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

export type ModalTableProps<
  DataSource,
  ParamsType,
  ValueType extends BaseEntity
> = {
  trigger: ReactNode;
  drawerTitle: ReactNode;
  drawerWidth?: string | number;
} & TriggerModalProps &
  CrudTableProps<DataSource, ParamsType, ValueType>;

export const ModalTable = <
  DataSource extends Record<string, unknown>,
  Params extends ParamsType,
  ValueType extends BaseEntity
>(
  props: ModalTableProps<DataSource, Params, ValueType>
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
