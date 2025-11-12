m -- Adicionar colunas à tabela book no MySQL

ALTER TABLE book ADD COLUMN classificacao VARCHAR(255);

ALTER TABLE book ADD COLUMN alugado BOOLEAN DEFAULT FALSE;

-- Atualizar tabela alunos para cadastro completo
ALTER TABLE alunos ADD COLUMN nome VARCHAR(255) NOT NULL;
ALTER TABLE alunos ADD COLUMN cpf VARCHAR(14) UNIQUE NOT NULL;
ALTER TABLE alunos ADD COLUMN data_nascimento DATE;

-- Adicionar coluna rfid à tabela alunos se não existir
ALTER TABLE alunos ADD COLUMN rfid VARCHAR(255) UNIQUE;

-- Reordenar coluna rfid para ficar após id
ALTER TABLE alunos MODIFY COLUMN rfid VARCHAR(255) DEFAULT NULL AFTER id;

-- CREATE TABLE `alunos` (
--    `id` bigint NOT NULL AUTO_INCREMENT,
--    `nome` varchar(255) DEFAULT NULL,
--    `cpf` varchar(255) DEFAULT NULL,
--    `data_nascimento` date DEFAULT NULL,
--    `rfid` varchar(255) DEFAULT NULL,
--    PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Criar tabela de usuários para autenticação
CREATE TABLE `usuarios` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `username` varchar(255) NOT NULL UNIQUE,
    `email` varchar(255) UNIQUE,
    `password` varchar(255) NOT NULL,
    `role` varchar(50) NOT NULL DEFAULT 'USER',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Adicionar coluna email_usuario à tabela usuarios existente
ALTER TABLE `usuarios` ADD COLUMN `email_usuario` VARCHAR(255) UNIQUE;

-- Inserir usuário padrão (senha: admin123 - em texto plano)
INSERT INTO `usuarios` (`username`, `password`, `role`) VALUES
('admin', 'admin123', 'ADMIN');
TRUNCATE Table usuarios;
