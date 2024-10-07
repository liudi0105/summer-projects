import {
  ActionType,
  ParamsType,
  ProColumns,
  ProForm,
  ProFormInstance,
  ProFormText,
  ProTable,
  ProTableProps,
} from "@ant-design/pro-components";
import { BaseEntity, BaseService } from "@common-module/common-api";
import { Drawer, DrawerProps, message, Popconfirm, Space } from "antd";
import { ReactNode, useRef, useState } from "react";
import { Button } from "./Button";
import { ButtonModalFrom } from "./Form";
import { TriggerModal, TriggerModalProps } from "./Modal";

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
  const { rowKey = "id", options = false } = props;
  return <ProTable {...props} rowKey={rowKey} options={options}></ProTable>;
};

export type CrudTableProps<
  DataType extends BaseEntity,
  ParamType extends ParamsType
> = {
  crud?: boolean;
  createForm?: ProFormInstance;
  updateForm?: ProFormInstance;
  service: BaseService<DataType>;
  columns: (ProColumns & {
    create?: boolean;
    readonly?: boolean;
  })[];
} & Omit<TableProps<DataType, ParamType>, "columns">;

export const CrudTable = <
  DataType extends BaseEntity,
  Params extends ParamsType
>(
  props: CrudTableProps<DataType, Params>
) => {
  const { toolbar = {}, crud = true, service } = props;
  const { actions = [] } = toolbar;

  const [createForm] = ProForm.useForm(props.createForm);

  const ref = useRef<ActionType>();

  const columns: CrudTableProps<DataType, Params>["columns"] = [
    ...props.columns,
    {
      dataIndex: "id",
      title: "ID",
      hidden: true,
      search: false,
      create: false,
      readonly: true,
    },
    {
      dataIndex: "createdBy",
      title: "Created By",
      hidden: true,
      search: false,
      create: false,
      readonly: true,
    },
    {
      dataIndex: "createdDate",
      title: "Created Date",
      hidden: true,
      search: false,
      create: false,
      readonly: true,
    },
    {
      dataIndex: "lastModifiedBy",
      title: "Last Modified By",
      hidden: true,
      search: false,
      create: false,
      readonly: true,
    },
    {
      dataIndex: "lastModifiedDate",
      title: "Last Modified Date",
      hidden: true,
      search: false,
      create: false,
      readonly: true,
    },
    {
      dataIndex: "version",
      title: "Version",
      hidden: true,
      search: false,
      create: false,
      readonly: true,
    },
    {
      title: "操作",
      search: false,
      width: 80,
      render: (_1, entity: BaseEntity, _2, action) => {
        return (
          <Space>
            <ButtonModalFrom<DataType>
              title="更新"
              buttonSize="small"
              buttonType="link"
              initialValues={entity}
              onFinish={async (values) => {
                await service.createOrUpdate(values);
                message.success("更新成功");
                await action?.reload();
                return true;
              }}
            >
              {columns
                .filter((v) => v.dataIndex)
                .map((v) => (
                  <ProFormText
                    readonly={v.readonly}
                    key={v.dataIndex?.toString()}
                    name={v.dataIndex}
                    label={v.title?.toString() ?? v.dataIndex}
                  />
                ))}
            </ButtonModalFrom>
            <Popconfirm
              title={`确定要删除[${entity.id}]吗？`}
              onConfirm={async () => {
                await service.deleteById(entity.id);
                message.success("删除成功");
                await action?.reload();
                return true;
              }}
            >
              <Button size="small" type="link" danger>
                删除
              </Button>
            </Popconfirm>
          </Space>
        );
      },
    },
  ];

  return (
    <Table
      {...props}
      size="small"
      actionRef={ref}
      columns={columns}
      request={async (params) => {
        const result = await service.listPaged({
          pageIndex: params.current ?? 1,
          pageSize: params.pageSize ?? 10,
        });
        return {
          data: result.content,
          total: result.totalElements,
        };
      }}
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
                ref.current?.reload();
                return true;
              }}
            >
              {columns
                .filter(
                  (v) => v.dataIndex && (v.create === undefined || v.create)
                )
                .map((v) => (
                  <ProFormText
                    key={v.dataIndex?.toString()}
                    name={v.dataIndex}
                    label={v.title?.toString() ?? v.dataIndex}
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
