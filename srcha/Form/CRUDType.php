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
        /*->add('id_client', EntityType::class, array(
            'class' => 'App\Entity\Jeu',
            'choice_label' => 'id_client'
        ))
        ->add('nom_client', EntityType::class, array(
            'class' => 'App\Entity\User',
            'choice_label' => 'nom'
        ))*/
        ->add('id_client', ChoiceType::class, array(
            'choices' => array('2' => '2', '3' => '3', '4' => '4', '5' => '5'
        ,'7' => '7', '8' => '8', '11' => '11',
         '15' => '15','16' => '16'),
        ))
        ->add('nom_client', ChoiceType::class, array(
            'choices' => array('yahmadi' => 'yahmadi', 'dfdgf' => 'dfdgf', 'zayet' => 'zayet',
             'asd' => 'asd', 'hamed' => 'hamed', 'Nidhal' => 'Nidhal'),
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
