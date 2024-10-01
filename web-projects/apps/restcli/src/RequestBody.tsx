import { UploadOutlined } from "@ant-design/icons"
import { Button, Flex, Input, Radio, Upload } from "antd"
import { useState } from "react"
import { RequestParameter } from "./RequestParameter"

export const RequestBody = () => {
  const [contentType, setContentType] = useState('json')

  return <Flex vertical gap={8}>
    <Radio.Group onChange={(e) => setContentType(e.target.value)} value={contentType}
      buttonStyle='solid' optionType='button' defaultValue='application/json' options={
        ['json', 'x-www-form-urlencoded', 'raw', 'binary'].map(v => ({ value: v, label: v }))
      } />
    {contentType == 'json' && <Input.TextArea rows={8} />}
    {contentType == 'x-www-form-urlencoded' && <RequestParameter />}
    {contentType == 'binary' && <Upload >
      <Button icon={<UploadOutlined />}>Choose File</Button>
    </Upload>}

  </Flex>
}