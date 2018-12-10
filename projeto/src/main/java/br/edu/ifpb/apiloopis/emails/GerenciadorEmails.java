package br.edu.ifpb.apiloopis.emails;

import br.edu.ifpb.apiloopis.entities.Evento;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GerenciadorEmails {

    public GerenciadorEmails() {
    }

    public void preparaEmail(Evento evento) {

        String titulo = "Lembrete - Novo evento da Loopis Jr";
        String corpo = "<img src='https://instagram.frec10-1.fna.fbcdn.net/vp/57a794f45abc7b72d2a7b97fdea14a2e/5CAE82D5/t51.2885-19/s320x320/36889194_201477703877861_8488926173902929920_n.jpg?_nc_ht=instagram.frec10-1.fna.fbcdn.net'/> "+
                "<h2>Gostaríamos de informá-lo sobre um novo evento da empresa Loopis Jr," +
                " onde você deve participar.</h2> \n" +
                "Título do evento: " + evento.getTitulo() +
                "\n Descrição: " + evento.getDescricao() +
                "\n Data e hora: " + evento.getData().toString() + ", as " + evento.getHora() + ".";

        List<String> emails = new ArrayList<>();
        evento.getFuncionariosEnvolvidos().forEach(f -> emails.add(f.getEmail()));
        if (!emails.isEmpty()) {
            String destinatarios = "";
            for (String e : emails) {
                destinatarios += e + ",";
            }
            enviar(destinatarios.substring(0, destinatarios.length() - 1), titulo, corpo);
        }
    }

    private void enviar(String destinatario, String titulo, String corpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("dac20181@gmail.com",
                                "D2!dac2018");
                    }
                });
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dac20181@gmail.com"));

            Address[] toUser = InternetAddress.parse(destinatario);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(titulo);
            message.setContent(corpo, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
