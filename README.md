# Kotlin Calculator 📱

Uma calculadora simples, moderna e funcional para Android desenvolvida utilizando **Kotlin** e **Jetpack Compose**. A aplicação adota as diretrizes do Material Design 3 e foi projetada para ter uma interface premium e responsiva.

## ✨ Recursos

- **Operações Básicas**: Adição, Subtração, Multiplicação e Divisão.
- **Funções Especiais**:
  - Limpar tudo (`AC`) e apagar último caractere (`⌫`).
  - Porcentagem (`%`) e alternância de sinal positivo/negativo (`±`).
- **Design Moderno e Responsivo**:
  - Grid de botões otimizado para aproveitar bem o espaço da tela.
  - Suporte nativo ao **Modo Escuro e Modo Claro** do sistema.
  - Ajuste dinâmico do tamanho do texto no display para evitar cortes em números grandes.
  - **Feedback tátil (Haptic Feedback)** ao pressionar os botões.
- **Qualidade de Código**: Suíte completa de testes unitários para garantir a confiabilidade dos cálculos matemáticos.

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Jetpack Compose**: Framework declarativo moderno para construção da interface nativa.
- **Material Design 3**: Biblioteca de componentes visuais do Android.
- **JUnit 4**: Utilizado para os testes automatizados da lógica de cálculo.

## 🚀 Como Executar o Projeto

1. Certifique-se de ter o **Android Studio** instalado.
2. Clone este repositório:
   ```bash
   git clone https://github.com/Luan-zanardo/kotlin-calculator.git
   ```
3. Abra a pasta do projeto no Android Studio.
4. Aguarde a sincronização do Gradle.
5. Execute em um Emulador ou Dispositivo Físico conectado.

## 🧪 Rodando os Testes

Para executar a suíte de testes unitários localmente, utilize o terminal no diretório raiz do projeto:
```bash
./gradlew test
```
*(No Windows, utilize `.\gradlew.bat test`)*
