import * as Pro from "@ant-design/pro-components";
import { BaseEntity } from "@common-module/common-api";
import { Button, ButtonProps } from "antd";

export type ModalFormProps<T> = {} & Pro.ModalFormProps<T>;

export const ModalForm = <T extends BaseEntity>(props: ModalFormProps<T>) => {
  const { layout = "horizontal", labelCol = { span: 6 }, width = 500 } = props;

  const [form] = Pro.ProForm.useForm(props.form);

  return (
    <Pro.ModalForm
      {...props}
      width={width}
      modalProps={{
        destroyOnClose: true,
      }}
      form={form}
      layout={layout}
      labelCol={labelCol}
    />
  );
};

export type ButtonModalFormProps<T> = {
  buttonSize?: ButtonProps["size"];
  buttonType?: ButtonProps["type"];
} & Omit<ModalFormProps<T>, "trigger">;

export const ButtonModalFrom = <T extends BaseEntity>(
  props: ButtonModalFormProps<T>
) => {
  const { buttonSize, buttonType, ...formProps } = props;

  return (
    <ModalForm<T>
      {...formProps}
      trigger={
        <Button size={buttonSize} type={buttonType}>
          {props.title}
        </Button>
      }
    />
  );
};
