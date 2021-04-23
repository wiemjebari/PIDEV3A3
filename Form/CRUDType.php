<?php

namespace App\Form;

use App\Entity\Jeu;
use Symfony\Component\Form\AbstractType;
use App\Repository\UserRepository;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\ChoiceList\ChoiceList;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CRUDType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
        ->add('id_client', ChoiceType::class, array(
            'choices' => array('1' => '1', '2' => '2', '3' => '3', '4' => '4', '5' => '5', '6' => '6'
        ,'7' => '7', '8' => '8', '9' => '9','10' => '10', '11' => '11', '12' => '12',
        '13' => '13', '14' => '14', '15' => '15','16' => '16', '17' => '17', '18' => '18'),
        ))
        ->add('nom_cat', ChoiceType::class, array(
            'choices' => array('Adventure' => 'Adventure', 'Meditation' => 'Meditation', 'Yoga' => 'Yoga'),
        ))
        ->add('nom_jeu', ChoiceType::class, array(
            'choices' => array('Snake' => 'Snake', 'Ping-Pong' => 'Ping-Pong'),
        ))
        ->add('niv_diff', ChoiceType::class, array(
            'choices' => array('1' => '1', '2' => '2', '3' => '3'),
        ))
            /*->add('id')
            ->add('id_client')
            ->add('nom_cat')
            ->add('nom_jeu')
            ->add('niv_diff')*/
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Jeu::class,
        ]);
    }
}
