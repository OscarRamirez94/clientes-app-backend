package com.example.clientes.auth;

public class JWTConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta";
	public static final String LLAVE_PRIVADA ="-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEogIBAAKCAQEAv18RiJ6bOpQ640f8Ekw/UPaKigWkTlKQPblk4cH7ZRM7ZWx5\r\n"
			+ "Vzx3UFRxWV95HHsw+KYVyw7jh80cm8WtVaQVxNGyaFnycKBSUwSBUaSF2VXOdXGy\r\n"
			+ "1V7AouTJ2XImCbt6NXgR9uEgsY05wIC7okiWq0bWVerFSCjHwyfQkg9f2H8GMCbV\r\n"
			+ "fDiB+26ahCll3njm1mhkcjkeDgBA0ojn4meZR8vJ7b85JpcreKH1ydNRhW8qWvDS\r\n"
			+ "LrRRpvcETRSXAgSdspdNhJOxtNgt4s/th9S3dvvI71u8S/MABI4nJGksCrgV2qp3\r\n"
			+ "iAvxJil3sCD/SQvhUvaz2XIKs3HJyjp2ZDJSkwIDAQABAoIBAHymsAUNSo8dfj4M\r\n"
			+ "5WQFxjg2XocIpb1eihWqbMugTt/HZyjpPyBTgmQQnMRaK8khdQSBKjDZwdry/293\r\n"
			+ "ky/1cTiCXnMaI5C43Oa2SIWlZInsGk7lgFb7IP75h4DbmWs9cpzhWNI2UdVU6Ak7\r\n"
			+ "0PwjzABEY9gS0+3sojG7/V8mJIgfFKl+p/s6sUAEnpCqLINNam+uo5/nCa3u8MJ6\r\n"
			+ "rcn2HzaFJ5Q0/DS/0HcOF813pbpN7v8mS1VOWxYbEIdfmt5q+bXGmg6PhaG6FoI+\r\n"
			+ "Ii9Qb6an1wst1sOrjc/rJSQSL0hFpl6syJdAqqCQiJBbk4JwBy1DJxuETiX9WckZ\r\n"
			+ "4TDR3AECgYEA5J5GFJTgTu+HA6/+tVGARhEWjhtney3S6bx/Gl8JywTpsqmr5POz\r\n"
			+ "pjK4IBVVzRG8cg3W4OhSz+hj/y6cgslNKR5eUYQdvL96pwYiImrd9gLRbCj1ZF1V\r\n"
			+ "cguStLj2EP2c2PG5oOlQcQ+Zq8PoAaxItT2DSWAdc/b3+5hXh5YRyckCgYEA1krB\r\n"
			+ "8a8ZwXiVRVV1KlQ0Ok+5WYOpFkayuKko/E9WRDTZFTHdf1GeBSLNukoVMsj6Po44\r\n"
			+ "lZI4iG8vYctHIUhq7tE/B8XfYgxlP+EpKUHjw20dteV1fgQYRLFAmRfU3FTx5AhM\r\n"
			+ "VUCk2CoqOX6e3lgKHnmQFFEnbvNKmTDPGuEC53sCgYAzrGaQrlnI3bD5r2T13LcO\r\n"
			+ "nzwm4KDSIbhjZlygPEz9W13rKRiWRlMbxKkDb28WS+T5LpGcAkb9gTxg6Est8IwF\r\n"
			+ "ODz/VcpW6Dyz1doPR3Jl8ZseRF+60LHIdj8xpADSNFCoZHKdoT6yX2FPhpdBEYzZ\r\n"
			+ "kk7DXfsqQ+8nKfbWyZ+5qQKBgBzdnRptN87z60eXp1y4UwC31IPWVGSHyXNOdhLm\r\n"
			+ "rPwrEkLkoK2R1PNkKMo7SHe60tqyVbTwKWVXdM57j21QT/czgIYPhXOzr7/XGLBS\r\n"
			+ "C9g7TpE+A4GiE5LNPaJhr26ukpbg90dUmd/0bhOolsXknhhUz6+s7t7nLtlL1doN\r\n"
			+ "DVMZAoGAIvN60+0/3Ghc6AqJBIlhy8qNL4ErIqYwSltlJvIeuffpOQc+9KejdpRW\r\n"
			+ "zbSFy2Eas64j1iaM+uRzUrwI1SxTipaFfZNkG/17WDl3LLx7t7zBhRJLjFIAZFNB\r\n"
			+ "Y5mYG0NG/IVEIzjSDZtdVn3GqvIAGkPBGqplM+g02lSqBUUHTi0=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	public static final String LLAVE_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv18RiJ6bOpQ640f8Ekw/\r\n"
			+ "UPaKigWkTlKQPblk4cH7ZRM7ZWx5Vzx3UFRxWV95HHsw+KYVyw7jh80cm8WtVaQV\r\n"
			+ "xNGyaFnycKBSUwSBUaSF2VXOdXGy1V7AouTJ2XImCbt6NXgR9uEgsY05wIC7okiW\r\n"
			+ "q0bWVerFSCjHwyfQkg9f2H8GMCbVfDiB+26ahCll3njm1mhkcjkeDgBA0ojn4meZ\r\n"
			+ "R8vJ7b85JpcreKH1ydNRhW8qWvDSLrRRpvcETRSXAgSdspdNhJOxtNgt4s/th9S3\r\n"
			+ "dvvI71u8S/MABI4nJGksCrgV2qp3iAvxJil3sCD/SQvhUvaz2XIKs3HJyjp2ZDJS\r\n"
			+ "kwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
