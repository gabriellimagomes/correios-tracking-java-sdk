[![Stories in Ready](https://badge.waffle.io/alexandregama/correios-tracking-java-sdk.png?label=ready&title=Ready)](https://waffle.io/alexandregama/correios-tracking-java-sdk)
# Correios Tracking Java SDK

Quer um resumo simples e prático? Visite a [página oficial](http://alexandregama.github.io/correios-tracking-java-sdk/) do SDK :)

## Correios Rastreio Java SDK.

Este SDK foi criada para facilitar a sua vida quando precisar fazer integração com o serviço de [Rastreios de Objetos](http://www2.correios.com.br/sistemas/rastreamento/) dos Correios \o/

O SDK é totalmente Free e Open Source! Como você deve ter notado, usamos o GitHub, então sinta-se à vontade para fazer alterações necessárias e [abrir um Pull Request](https://help.github.com/articles/using-pull-requests/) pra gente :)

O SDK não tem alguma funcionalidade que você deseja? Não se preocupe, basta criar uma [issue](https://github.com/alexandregama/correios-tracking-java-sdk/issues) explicando o que você gostaria e implementaremos pra você :)

## Usando o SDK

Veja como é simples usar o SDK para Rastrear um objeto nos correios em 2 passos:

1 - Com as suas credenciais em mãos, instancie o seguinte objeto:

```java
CorreiosCredentials credentials = new CorreiosCredentials("username", "password");
```

2 - Em seguida basta invocar o método para retornar os Eventos do objeto desejado:

```java
CorreiosRastreioClientApi correiosApi = new CorreiosRastreioClientApi(credentials);

PacoteTracker pacoteTracker = correiosApi.buscaPacoteTrackerUsando("codigo_do_seu_objeto").emPortugues().comTodosOsEventos().build();
```

O código do seu objeto deve ser algo parecido com **DU500853238BR**.

O código completo fica como o a seguir:

```java
	@Test
	public void deveriaRetornarOsEventosAPartirDeUmTrackingCode() throws Exception {
		CorreiosCredentials credentials = new CorreiosCredentials("058457", "yhe8sa11");
		
		CorreiosRastreioClientApi correiosApi = new CorreiosRastreioClientApi(credentials);

		PacoteTracker pacoteTracker = correiosApi.buscaPacoteTrackerUsandoOCodigo("DU500853237BR").emPortugues().comTodosOsEventos().getPacoteTracker();
		
		System.out.println(pacoteTracker);
	}
```

Note que usamos uma [Interface Fluent](https://en.wikipedia.org/wiki/Fluent_interface) que já indica pra você quais métodos devem ser invocados e nem deixa você errar! E o resto fazemos internamente pra você! Que demais né? \o/

## Buscando mais de um Objeto

Também é possível buscar mais de um Objeto através de uma lista de Códigos, como é mostrado a seguir:

```java
@Test
	public void deveriaRetornarOsEventosAPartirDeUmaListaDeTrackingCodes() throws Exception {
		CorreiosCredentials credentials = new CorreiosCredentials("username", "password");
		
		CorreiosRastreioClientApi correiosApi = new CorreiosRastreioClientApi(credentials);
		
		List<String> trackingCodes = new ArrayList<>();
		trackingCodes.add("DU500853237BR");
		trackingCodes.add("DU496842125BR");
		
		List<PacoteTracker> listaDeEventos = correiosApi.buscaPacoteTrackerPelaListaDeTrackings(trackingCodes).emPortugues().comTodosOsEventos().getListaDePacotesTracker();
		
		System.out.println(listaDeEventos);
	}
```

Note que agora temos uma lista de PacoteTracker, que por sua vez é o objeto que contém todas as informações que você precisa! Awesome! \o/

## Informações de Retorno

Os Correios enviam diversas informações relacionadas ao objeto que está sendo pesquisado. 

O objeto PacoteTracker é responsável por guardar essas informações, dê uma olhadinha na documentação na Wiki ;)

## Authors and Contributors

Autor: 
Alexandre Gama (@alexandregama)

## Support or Contact

Está tendo algum problema no uso do SDK? Basta abrir uma [issue](https://github.com/alexandregama/correios-tracking-java-sdk/issues) indicando os passos do seu problema e responderemos o mais breve possível
