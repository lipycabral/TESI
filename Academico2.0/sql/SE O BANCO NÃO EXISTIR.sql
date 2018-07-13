-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema academico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema academico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `academico` DEFAULT CHARACTER SET utf8 ;
USE `academico` ;

-- -----------------------------------------------------
-- Table `academico`.`cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`cursos` (
  `codigo` INT(3) NOT NULL,
  `nome` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Tabela de cursos';


-- -----------------------------------------------------
-- Table `academico`.`alunos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`alunos` (
  `matricula` BIGINT(11) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `fone` VARCHAR(11) NULL DEFAULT NULL,
  `endereco` VARCHAR(60) NULL,
  `cep` VARCHAR(7) NULL DEFAULT NULL,
  `sexo` CHAR(1) NULL DEFAULT NULL,
  `curso` INT(3) NOT NULL,
  PRIMARY KEY (`matricula`),
  INDEX `fk_Aluno_curso1` (`curso` ASC),
  CONSTRAINT `fk_Aluno_curso1`
    FOREIGN KEY (`curso`)
    REFERENCES `academico`.`cursos` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Tabela de alunos';


-- -----------------------------------------------------
-- Table `academico`.`centros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`centros` (
  `sigla` VARCHAR(5) NOT NULL,
  `nome` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`sigla`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Tabela de centros';


-- -----------------------------------------------------
-- Table `academico`.`disciplinas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`disciplinas` (
  `codigo` VARCHAR(8) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  `ch` INT(3) NOT NULL DEFAULT '60',
  `centro` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_disciplinas_centros1_idx` (`centro` ASC),
  CONSTRAINT `fk_disciplinas_centros1`
    FOREIGN KEY (`centro`)
    REFERENCES `academico`.`centros` (`sigla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Tabela de disciplinas';


-- -----------------------------------------------------
-- Table `academico`.`professores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`professores` (
  `matricula` INT(10) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `rg` BIGINT(11) NOT NULL,
  `cpf` BIGINT(11) NOT NULL,
  `endereco` VARCHAR(60) NULL DEFAULT NULL,
  `fone` VARCHAR(11) NULL DEFAULT NULL,
  `centro` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`matricula`),
  INDEX `fk_professor_centro` (`centro` ASC),
  CONSTRAINT `fk_professor_centro`
    FOREIGN KEY (`centro`)
    REFERENCES `academico`.`centros` (`sigla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Tabela de professores';


-- -----------------------------------------------------
-- Table `academico`.`estrutura_curricular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`estrutura_curricular` (
  `curso` INT(3) NOT NULL,
  `disciplina` VARCHAR(8) NOT NULL,
  `periodo` INT(2) NOT NULL,
  PRIMARY KEY (`curso`, `disciplina`),
  INDEX `fk_cursos_has_disciplinas_disciplinas1_idx` (`disciplina` ASC),
  INDEX `fk_cursos_has_disciplinas_cursos1_idx` (`curso` ASC),
  CONSTRAINT `fk_cursos_has_disciplinas_cursos1`
    FOREIGN KEY (`curso`)
    REFERENCES `academico`.`cursos` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cursos_has_disciplinas_disciplinas1`
    FOREIGN KEY (`disciplina`)
    REFERENCES `academico`.`disciplinas` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `academico`.`turmas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`turmas` (
  `curso` INT(3) NOT NULL,
  `disciplina` VARCHAR(8) NOT NULL,
  `ano` INT(4) NOT NULL,
  `semestre` INT(2) NOT NULL,
  `professor` INT(10) NOT NULL,
  `vagas` INT(3) NOT NULL,
  INDEX `fk_turmas_professores1_idx` (`professor` ASC),
  PRIMARY KEY (`curso`, `disciplina`, `ano`, `semestre`),
  CONSTRAINT `fk_turmas_professores1`
    FOREIGN KEY (`professor`)
    REFERENCES `academico`.`professores` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turmas_estrutura_curricular1`
    FOREIGN KEY (`curso` , `disciplina`)
    REFERENCES `academico`.`estrutura_curricular` (`curso` , `disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academico`.`matricula_curricular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academico`.`matricula_curricular` (
  `curso` INT(3) NOT NULL,
  `disciplina` VARCHAR(8) NOT NULL,
  `ano` INT(4) NOT NULL,
  `semestre` INT(2) NOT NULL,
  `aluno` BIGINT(11) NOT NULL,
  `faltas` INT(3) NULL,
  `n1` DECIMAL(3,2) NULL,
  `n2` DECIMAL(3,2) NULL,
  `nf` DECIMAL(3,2) NULL,
  PRIMARY KEY (`curso`, `disciplina`, `ano`, `semestre`, `aluno`),
  INDEX `fk_alunos_has_turmas_alunos1_idx` (`aluno` ASC),
  INDEX `fk_matricula_curricular_turmas1_idx` (`curso` ASC, `disciplina` ASC, `ano` ASC, `semestre` ASC),
  CONSTRAINT `fk_alunos_has_turmas_alunos1`
    FOREIGN KEY (`aluno`)
    REFERENCES `academico`.`alunos` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matricula_curricular_turmas1`
    FOREIGN KEY (`curso` , `disciplina` , `ano` , `semestre`)
    REFERENCES `academico`.`turmas` (`curso` , `disciplina` , `ano` , `semestre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
