
### Symmetric Encryption
เป็นการเข้ารหัสและถอดรหัสแบบกุญแจ 1 ดอก โดยผู้ส่งและผู้รับจะใช้กุญแจดอกเดียวกันในการเข้ารหัสและถอดรหัส 
ซึ่งกุญแจนี้จะถูกเก็บเป็นความลับและเข้าถึงได้เฉพาะผู้ส่งและผู้รับเท่านั้น กุญแจในรูปแบบนี้มีชื่อเรียกอีกอย่างหนึ่งว่า “Secret Key”

### Asymmetric Encryption
เป็นการเข้ารหัสและถอดรหัสข้อมูลแบบกุญแจ 2 ดอก โดยผู้ส่งจะมีกุญแจ 1 ดอกในการใส่รหัสข้อมูล และผู้รับก็จะมีกุญแจอีก 1 ดอกในการถอดรหัสข้อมูล โดยกุญแจ 2 ดอกนี้จะเรียกว่า “กุญแจคู่ (Key Pair)” ซึ่งแบ่งออกเป็น กุญแจสาธารณะ (Public Key) ที่จะสามารถประกาศให้ผู้อื่นทราบหรือสามารถแจกจ่ายออกไปในที่สาธารณะได้ ส่วนกุญแจส่วนตัว (Private Key) จะต้องเก็บรักษาไว้กับเจ้าของกุญแจเท่านั้นและห้ามไม่ให้ใครรู้ การทำงานของกุญแจทั้ง 2 ดอกนี้จะทำคู่กันคือถ้าเราใช้ Public Key ในการเข้ารหัสลับก็จะต้องใช้ Private Key ในการถอดรหัสลับและหากใช้ Private Key ในการเข้ารหัสลับก็จะต้องใช้ Public Key ในการถอดรหัสลับเช่นกัน

---
### Reference
- [ตัวอย่างการเขียน symmetric java](https://www.tutorialspoint.com/symmetric-encryption-cryptography-in-java)
- [ตัวอย่างการเขียน asymmetric java](https://gregorycernera.medium.com/encrypting-and-decrypting-a-message-using-asymmetric-keys-with-java-explained-step-by-step-with-54fced36118a)
- [เรียนรู้เกี่ยวกับ Infrastructure](https://www.jittagornp.me/blog/public-key-infrastructure/?series=pki)
- [Symmetric Encryption VS Asymmetric Encryption](https://www.ert.co.th/symmetric-encryption-vs-asymmetric-encryption/)