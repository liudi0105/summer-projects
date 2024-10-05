import * as Pro from "@ant-design/pro-components";
import { BaseEntity } from "@common-module/common-api";
import { Button } from "antd";
import { ReactNode } from "react";
import { TriggerModal } from "./Modal";

export type ModalFormProps<T> = {
  trigger: ReactNode;
  width?: number;
} & Pro.ProFormProps<T>;

export const ModalForm = <T extends BaseEntity>(props: ModalFormProps<T>) => {
  const {
    layout = "horizontal",
    labelCol = { span: 6 },
    width = 500,
    onFinish,
  } = props;

  const [form] = Pro.ProForm.useForm(props.form);

  return (
    <TriggerModal
      {...props}
      width={width}
      destroyOnClose={true}
      onOk={async () => {
        const values = await form?.validateFields();
        onFinish && (await onFinish(values));
      }}
    >
      <Pro.ProForm<T>
        form={form}
        layout={layout}
        labelCol={labelCol}
        {...props}
        submitter={false}
      />
    </TriggerModal>
  );
};

export type ButtonModalFormProps<T> = Omit<ModalFormProps<T>, "trigger">;

export const ButtonModalFrom = <T extends BaseEntity>(
  props: ButtonModalFormProps<T>
) => {
  return <ModalForm<T> {...props} trigger={<Button>{props.title}</Button>} />;
};
